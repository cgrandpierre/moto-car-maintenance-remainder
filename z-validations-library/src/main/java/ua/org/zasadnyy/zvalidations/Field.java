
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

import android.widget.EditText;

import java.util.LinkedList;
import java.util.List;

import ua.org.zasadnyy.zvalidations.validations.Validation;

public class Field {

    private final List<Validation> mValidations = new LinkedList<Validation>();
    private final EditText mTextView;

    private Field(final EditText textView) {
        this.mTextView = textView;
    }

    public static Field using(final EditText textView) {
        return new Field(textView);
    }

    public Field validate(final Validation what) {
        mValidations.add(what);
        return this;
    }

    public EditText getTextView() {
        return mTextView;
    }

    public FieldValidationResult validate() {
        final FieldValidationResult fieldValidationResult = new FieldValidationResult();
        for (final Validation validation : mValidations) {
            fieldValidationResult.addValidationResult(validation.validate(this));
        }
        return fieldValidationResult;
    }

}
