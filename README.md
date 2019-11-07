# pinKeyboard

  For Android 

  動態的產生隨機排列順序的數字鍵盤
  可以指定Dialog title

##使用

```
    SmartcardDialog dialog = new SmartcardDialog();

    dialog.setTitle("變更密碼");
    dialog.setDialogType(SmartcardDialog.CHANGE_PIN);
    dialog.setLogLevel(Log2.LogLevels.DEBUG);
    //禁止點選Dailog外部或返回鍵時關閉Dialog
    dialog.setCancelable(false);

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