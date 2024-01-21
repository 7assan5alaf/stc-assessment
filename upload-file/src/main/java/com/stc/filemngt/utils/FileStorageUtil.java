package com.stc.filemngt.utils;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class FileStorageUtil {

	public static byte[] compressFile(byte[] file) {
		Deflater deflater = new Deflater();
		deflater.setLevel(Deflater.BEST_COMPRESSION);
		deflater.setInput(file);
		deflater.finish();
		ByteArrayOutputStream stream = new ByteArrayOutputStream(file.length);
		byte[] dataHolder = new byte[4 * 1024];
		while (!deflater.finished()) {
			int size = deflater.deflate(dataHolder);
			stream.write(dataHolder, 0, size);
		}
		try {
			stream.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return stream.toByteArray();
	}

	public static byte[] dcompressFile(byte[] file) {
		Inflater inflater = new Inflater();
		inflater.setInput(file);
		ByteArrayOutputStream stream = new ByteArrayOutputStream(file.length);
		byte[] dataHolder = new byte[4 * 1024];
		try {
			while (!inflater.finished()) {
				int size = inflater.inflate(dataHolder);
				stream.write(dataHolder, 0, size);
			}

			stream.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return stream.toByteArray();
	}
}
