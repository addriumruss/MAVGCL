/****************************************************************************
 *
 *   Copyright (c) 2017,2018 Eike Mansfeld ecm@gmx.de. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 * 3. Neither the name of the copyright holder nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS
 * OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
 * ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 ****************************************************************************/

package com.comino.flight.model.converter;

import java.util.Map;

import com.comino.msp.utils.MSPMathUtils;

public class QuaternionConverter extends SourceConverter {

	private String ulogKeyFigure = null;
	private int    index;
	private float euler[] = new float[3];
	private float q[]     = new float[4];

	@Override
	public void setParameter(String kfname, String[] params) {
		this.ulogKeyFigure = params[0];
		this.index = Integer.parseInt(params[1]);
	}

	public QuaternionConverter() {
		super();
	}

	@Override
	public double convert(Map<String,Object> ulogdata) {
		try {
			q[0] = (float)(Float)ulogdata.get(ulogKeyFigure+"[0]");
			q[1] = (float)(Float)ulogdata.get(ulogKeyFigure+"[1]");
			q[2] = (float)(Float)ulogdata.get(ulogKeyFigure+"[2]");
			q[3] = (float)(Float)ulogdata.get(ulogKeyFigure+"[3]");
		} catch(Exception e) {
			return euler[index];
		}
		MSPMathUtils.eulerAnglesByQuaternion(euler, q);
		return euler[index];
	}

	@Override
	public String toString() {
		return ulogKeyFigure+"("+index+")";
	}

}
