/*
 *    This program is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 2 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program; if not, write to the Free Software
 *    Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */
package mulan.evaluation.measure;

import java.io.Serializable;
import mulan.classifier.MultiLabelOutput;
import mulan.core.ArgumentNullException;
import weka.core.SerializedObject;

/**
 * 
 * @author Grigorios Tsoumakas
 */
public abstract class MeasureBase implements Measure, Serializable {

    public final void update(MultiLabelOutput prediction, boolean[] truth) {
        if (prediction == null) {
            throw new ArgumentNullException("Prediction is null");
        }
        if (truth == null) {
            throw new ArgumentNullException("Ground truth is null");
        }
        updateInternal(prediction, truth);
    }

    /**
     * Returns a string with the value of a measure
     *
     * @return string representation of the value of a measure
     */
    @Override
    public String toString() {
        double value = Double.NaN;
        try {
            value = getValue();
        } catch (Exception ex) {
        } 
        return getName() + ": " + String.format("%.4f", value);
    }

    /**
     * Updates the measure based on an example
     *
     * @param prediction the output of the algorithm for the example
     * @param truth the ground truth of the example
     */
    protected abstract void updateInternal(MultiLabelOutput prediction, boolean[] truth);

    @Override
    public Measure makeCopy() throws Exception {
        return (Measure) new SerializedObject(this).getObject();
    }
}