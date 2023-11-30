package gtsi.sdp.windowsos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import gtsi.sdp.windowsos.models.Classroom;
import gtsi.sdp.windowsos.models.ClassroomManager;
import gtsi.sdp.windowsos.models.Task;
import gtsi.sdp.windowsos.models.TaskManager;

public class MainActivity extends AppCompatActivity {

    private RecyclerView classroom_list_view;
    private ClassroomItemAdapter adapter;
    private ImageView icon_view;
    private TextView test_text;

    private ServerSocket server_socket;
    Thread server_tread = null;
    private Handler update_info_handler;

    private RecyclerView.AdapterDataObserver observer = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            if (adapter.getItemCount() == 0) {
                icon_view.setImageResource(R.drawable.all_done);
            }
            else {
                icon_view.setImageResource(R.drawable.reminder);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test_text = findViewById(R.id.test_text);

        classroom_list_view = findViewById(R.id.classroom_list);
        icon_view = findViewById(R.id.icon_state);

        adapter = new ClassroomItemAdapter();
        adapter.registerAdapterDataObserver(observer);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        classroom_list_view.setLayoutManager(manager);
        classroom_list_view.setAdapter(adapter);

        Button add_button = findViewById(R.id.add_button);
        FloatingActionButton profile_button = findViewById(R.id.user_profile);

        // if use sensor, change code below.
        // Do not delete
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Classroom classroom = new Classroom(401 + adapter.getItemCount(), "window not close", true);
                classroom.setCompleted(false);
                adapter.add_item(classroom);
            }
        });

        profile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        update_info_handler = new Handler(Looper.getMainLooper());
        this.server_tread = new Thread(new ServerThread());
        this.server_tread.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh data or perform any other actions needed when the activity is resumed
        adapter.notifyDataSetChanged();
    }

    class ServerThread implements Runnable {
        @Override
        public void run() {
            update_info_handler.post(new UpdateUIThread("start listening"));
            Socket socket;
            try {
                server_socket = new ServerSocket(8080);
                update_info_handler.post(new UpdateUIThread("start server"));
                while (!Thread.currentThread().isInterrupted()) {
                    socket = server_socket.accept();
                    try (DataInputStream dataInputStream = new DataInputStream(socket.getInputStream())) {
                        byte[] buffer = new byte[1024];
                        int bytesRead = dataInputStream.read(buffer, 0, buffer.length);
                        if (bytesRead != -1) {
                            String hexData = bytesToHex(buffer, bytesRead);
                            update_info_handler.post(new UpdateUIThread(hexData));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    };
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class UpdateUIThread implements Runnable {
        private String msg;

        public UpdateUIThread(String str) {
            this.msg = str;
        }

        @Override
        public void run() {
            test_text.setText(msg);
            if (msg.equals("310d")) {
                if (!adapter.inList(408, "Window open")) {
                    adapter.add_item(new Classroom(408, "Window open", true));

                    ExecutorService executor = Executors.newSingleThreadExecutor();
                    Handler handler = new Handler(Looper.getMainLooper());

                    executor.execute(() -> {
                        // Background work: sending the email
                        sendEmail("lliu484@gatech.edu", "New Task!", "Window in room408 is open!");

                        handler.post(() -> {
                            // UI Thread work: update the UI after sending the email, if necessary
                            // For example, show a Toast or update a TextView
                        });
                    });
                }
            }
            else if (msg.equals("300d")) {
                adapter.remove(408, "Window open");
                TaskManager.getInstance().setCompleteTaskByFeature(408, "Window open");

            }
        }
    }

    private String bytesToHex(byte[] bytes, int length) {
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < length; i++) {
            String hex = Integer.toHexString(0xff & bytes[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    private void sendEmail(String toEmail, String subject, String message) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.163.com");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                Log.i("Email", "Auth");
                return new PasswordAuthentication("tester6262@163.com", "NFEGYJDKMWFHEFVA");
            }
        });

        try {
            MimeMessage mm = new MimeMessage(session);
            mm.setFrom(new InternetAddress("tester6262@163.com", "Monitor"));
            mm.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            mm.setSubject(subject);
            mm.setText(message);
            Transport.send(mm);
            Log.i("Email", "sent");
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}