# AsyncTaskDemo
四种异步实现方式，简单的延迟跳转界面，可用于应用启动跳转<br>
1、Thread</br>
``` Android
new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(2000);
                    toMain();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
```
</br>
2、Runnabl</br>

private void runnable() {
        MyRunnable runnable = new MyRunnable();
        runnable.run();
}
class  MyRunnable implements Runnable{
         @Override
         public void run() {
             try {
                 Thread.sleep(2000);
                 toMain();
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
}



</br>
3、AsyncTask</br>
AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                try {
                    Thread.sleep(((int)objects[0]));
                    toMain();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
}.execute(2000);
   </br>
4、Handler</br>
Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            toMain();
        }
};
private void handler() {
        handler.sendEmptyMessageDelayed(0,2000);
}
    
 ```
