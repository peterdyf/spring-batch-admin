/*
 * Copyright 2009-2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.batch.admin.util;

public class CumulativeHistory {

	private int count;
	private double sum;
	private double sumSquares;
	private double min;
	private double max;

	public void append(double value) {
		if (value>max || count==0) max = value;
		if (value<min || count==0) min = value;
		sum += value;
		sumSquares += value * value;
		count++;
		// TODO: percentiles and histograms
	}
	
	public int getCount() {
		return count;
	}

	public double getMean() {
		return sum / count;
	}

	public double getStandardDeviation() {
		double mean = getMean();
		return Math.sqrt(sumSquares / count - mean * mean);
	}

	public double getMax() {
		return max;
	}

	public double getMin() {
		return min;
	}
	
	@Override
	public String toString() {
		return String.format("[N=%d, min=%f, max=%f, mean=%f, sigma=%f]", count, min, max, getMean(), getStandardDeviation());
	}

}
