package com.jiushang.android11;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * @author: wxj
 * @date: 2021/5/7
 * @description:
 */
public class ViewBinder {
    void binder(EditText editText, final TextAttr textAttr) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(editable!=null&&!editable.toString().equals(textAttr.getText())){
                    textAttr.setText(editable.toString());
                }

            }
        });
    }

    public static class TextAttr {
        String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

    }
}
