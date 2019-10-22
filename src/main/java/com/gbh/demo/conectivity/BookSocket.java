package com.gbh.demo.conectivity;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public interface BookSocket<T> {
    default void send(BookCompressFormatData<T>  data, Socket socket) throws IOException {
        OutputStream outputStream = socket.getOutputStream();

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        objectOutputStream.writeObject(data);

        socket.close();
    }

}
