package com.bookverse.packapps.utils;

import java.awt.Component;
import java.util.concurrent.Callable;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import com.bookverse.packapps.utils.ui.Alerts;
import com.bookverse.packapps.utils.ui.LoadingDialog;

public class LoadingUtil {

  public static <T> T executeWithLoading(Component parent, Callable<T> task) {
    LoadingDialog loadingDialog = new LoadingDialog(SwingUtilities.getWindowAncestor(parent));

    SwingWorker<T, Void> worker = new SwingWorker<>() {
      @Override
      protected T doInBackground() throws Exception {
        return task.call();
      }

      @Override
      protected void done() {
        loadingDialog.dispose();
      }
    };

    worker.execute();
    loadingDialog.setVisible(true);

    try {
      return worker.get();
    } catch (Exception e) {
      Alerts.error(e, "Error Loading");
      return null;
    }
  }
}
