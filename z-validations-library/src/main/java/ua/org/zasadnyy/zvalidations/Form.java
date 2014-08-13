
/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2013 Vitaliy Zasadnyy
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package ua.org.zasadnyy.zvalidations;

import android.app.Activity;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class Form {

    private final List<Field> mFields = new ArrayList<Field>();
    private ValidationFailedRenderer mValidationFailedRenderer;
    private final Activity mActivity;

    public Form(final Activity activity) {
        this.mActivity = activity;
        this.mValidationFailedRenderer = new TextViewValidationFailedRenderer();
    }

    public void addField(final Field field) {
        mFields.add(field);
    }

    public void setValidationFailedRenderer(final ValidationFailedRenderer renderer) {
        this.mValidationFailedRenderer = renderer;
    }

    public ValidationFailedRenderer getValidationFailedRenderer() {
        return this.mValidationFailedRenderer;
    }

    public boolean isValid() {
        boolean isValid = true;
        mValidationFailedRenderer.clear();

        for (final Field field : mFields) {
            final FieldValidationResult result = field.validate();

            if (!result.isValid()) {
                final ValidationResult firstFailedValidation = result.getFailedValidationResults().get(0);
                final EditText textView = firstFailedValidation.getTextView();
                textView.requestFocus();
                textView.selectAll();

                FormUtils.showKeyboard(mActivity, textView);

                mValidationFailedRenderer.showErrorMessage(firstFailedValidation);

                isValid = false;
                break;
            }
        }

        return isValid;
    }
}
