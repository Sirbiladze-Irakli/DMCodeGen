import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.datamatrix.DataMatrixWriter;

public class DMCode {

	/**
	 * Method that generate a Data Matrix code as byte array which could be consumed by a birt image item
	 * @param text
	 * @param width (pixels)
	 * @param height (pixels)
	 * @return
	 * @throws WriterException
	 * @throws IOException
	 */
	public static byte[] getDMCodeImage(String text, int width, int height) throws WriterException, IOException {
		// No margin around the code
		Map<EncodeHintType, Integer> hints = new HashMap<>();
		hints.put(EncodeHintType.MARGIN, 0);

		// Use the code you want, here QR_CODE
		DataMatrixWriter dataMatrixWriter = new DataMatrixWriter();
		BitMatrix bitMatrix = dataMatrixWriter.encode(text, BarcodeFormat.DATA_MATRIX, width, height, hints);

		// Generate the PNG which include the code
		ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
		MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);

		// Return the PNG as byte array
		return pngOutputStream.toByteArray();
	}

	public static void main(String[] args) {
		byte[] array = new byte[1000000];
		try {
			array = DMCode.getDMCodeImage("12341234", 123, 123);
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}