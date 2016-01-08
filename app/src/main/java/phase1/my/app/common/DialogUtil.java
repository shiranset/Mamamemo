package phase1.my.app.common;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;

/**
 * Created by Owner on 16/11/2014.
 */
public class DialogUtil {

    public static void createInputDialog(Context context, String title, ViewEditTextI text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final EditText input = new EditText(context);

        builder.setTitle(title);
       input.setText(text.getText());
        builder.setView(input);
        builder.setCancelable(false);
        builder.setPositiveButton("OK",new OkListener(text,input));
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.show();

    }

    private static class OkListener implements DialogInterface.OnClickListener {

        private ViewEditTextI text;
        private EditText input;
        public OkListener(ViewEditTextI text, EditText input) {
            this.text = text;
            this.input = input;
        }

        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            text.setText(input.getText().toString());
        }
    }

}
