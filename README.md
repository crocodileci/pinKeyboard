# pinKeyboard

##使用

```
    SmartcardDialog dialog = new SmartcardDialog();
    dialog.setOnInputCompleteListener(new SmartcardDialog.InputCompleteListener() {
        @Override
        public void inputComplete(boolean isCanceled, String pinCode, String pinCode_new1, String pinCode_new2) {
            if(isCanceled){
               showToast("User Canceled");
            }else{
               showToast("pinCode: " + pinCode + " pinCode_new1: " + pinCode_new1 + " pinCode_new2: " + pinCode_new2);
            }
        }
    });
    dialog.show();
```