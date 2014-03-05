package com.jason.Task;

import android.os.AsyncTask;
import android.util.Log;

import com.jason.Interface.UIInterface;
import com.jason.Network.Httper;

public class MyAsyncTask extends AsyncTask<String, Integer, String> {
	// onPreExecute����������ִ�к�̨����ǰ��һЩUI����
	UIInterface fragment;

	public MyAsyncTask(UIInterface fragment) {
		this.fragment = fragment;
	}

	@Override
	protected void onPreExecute() {

	}

	// doInBackground�����ڲ�ִ�к�̨����,�����ڴ˷������޸�UI
	@Override
	protected String doInBackground(String... params) {
		return Httper.get(params[0]);
	}

	// onProgressUpdate�������ڸ��½�����Ϣ
	@Override
	protected void onProgressUpdate(Integer... progresses) {
		// progressBar.setProgress(progresses[0]);
		// textView.setText("loading..." + progresses[0] + "%");
	}

	// onPostExecute����������ִ�����̨��������UI,��ʾ���
	@Override
	protected void onPostExecute(String result) {
		if (result != null) {
			fragment.updateData(result);
			fragment.updateUI();
		}
	}

	// onCancelled����������ȡ��ִ���е�����ʱ����UI
	@Override
	protected void onCancelled() {

	}
}