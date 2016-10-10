package google.android.com.myapplicationthread;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button launchAsync;
    private ProgressBar progressBar;
    private myDownloadTask downloadTask;
//    private DownloadHandler downloadHandler;
//    private DownloadThread downloadThread;
//    private static final int MESSAGE_PRE_EXECUTE = 1;
//    private static final int MESSAGE_PROGRESS_UPDATE = 2;
//    private static final int MESSAGE_POST_EXECUTE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String uri = "";

        launchAsync = (Button) findViewById(R.id.launch_async);
        launchAsync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadTask = new myDownloadTask();
                downloadTask.execute(uri);
            }
        });
        progressBar = (ProgressBar) findViewById(R.id.progress);

    }

    public class myDownloadTask extends AsyncTask<String, Integer, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            launchAsync.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            String uri = params[0];
            String result = "";
            for (int i = 1; i <= 10; ++i) {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i * 10);
                result += i;
            }
            return result;
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            progressBar.setProgress(progress[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Toast.makeText(MainActivity.this, "Fin de l'exÃ©cution du traitement en arriÃ¨re-plan", Toast.LENGTH_LONG).show();
            launchAsync.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }

//    public class Tache extends AsyncTask<String, Integer, String> {
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            launchAsync.setVisibility(View.GONE);
//            progressBar.setVisibility(View.VISIBLE);
//        }
//
//        @Override
//        protected String doInBackground(String... params) {
//            return null;
//        }
//    }

//    public class DownloadThread extends Thread {
//        @Override
//        public void run() {
//            sendPreExecuteMessage();
//
//            for (int i = 1; i <= 10; ++i) {
//                try {
//                    Thread.sleep(1000L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                sendProgressMessage(i);
//            }
//
//            sendPostExecuteMessage();
//        }
//        private void sendPostExecuteMessage() {
//            Message postExecuteMsg = new Message();
//            postExecuteMsg.what = MESSAGE_POST_EXECUTE;
//            downloadHandler.sendMessage(postExecuteMsg);
//        }
//        private void sendPreExecuteMessage() {
//            Message preExecuteMsg = new Message();
//            preExecuteMsg.what = MESSAGE_PRE_EXECUTE;
//            downloadHandler.sendMessage(preExecuteMsg);
//        }
//
//        private void sendProgressMessage(int i) {
//            Message progressMsg = new Message();
//            progressMsg.what = MESSAGE_PROGRESS_UPDATE;
//            progressMsg.arg1 = i * 10;
//            downloadHandler.sendMessage(progressMsg);
//        }
//
//    }
//
//    public class DownloadHandler extends Handler {
//
//        @Override
//        public void handleMessage(Message msg) {
//            // TODO Auto-generated method stub
//            super.handleMessage(msg);
//            switch (msg.what) {
//                case MESSAGE_PRE_EXECUTE:
//                    downloadOnPreExecute();
//                    break;
//                case MESSAGE_PROGRESS_UPDATE:
//                    downloadOnProgressUpdate(msg.arg1);
//                    break;
//                case MESSAGE_POST_EXECUTE:
//                    downloadOnPostExecute();
//                    break;
//                default:
//                    break;
//
//            }
//        }
//        private void downloadOnPreExecute() {
//            launchAsync.setVisibility(View.GONE);
//            progressBar.setVisibility(View.VISIBLE);
//        }
//
//        protected void downloadOnProgressUpdate(int progress) {
//            MainActivity.this.progressBar.setProgress(progress);
//        }
//
//        protected void downloadOnPostExecute() {
//            Toast.makeText(MainActivity.this,
//                    "Fin de l’exécution du traitement en arrière-plan",
//                    Toast.LENGTH_LONG).show();
//            launchAsync.setVisibility(View.VISIBLE);
//            progressBar.setVisibility(View.GONE);
//        }
//
//    }

}


