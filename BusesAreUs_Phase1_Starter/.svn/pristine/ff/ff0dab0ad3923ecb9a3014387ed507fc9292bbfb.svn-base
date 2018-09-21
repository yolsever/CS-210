package ca.ubc.cs.cpsc210.translink.providers;

import java.io.*;

/**
 * Data provider where data source is a file in Java (non-Android) environment
 */
public class FileDataProvider extends AbstractFileDataProvider {
    private String fileName;

    /**
     * Constructs data provider where source is read from file with given name
     *
     * @param fileName   the name of the file containing the source data
     */
    public FileDataProvider(String fileName) {
        this.fileName = "data/" + fileName;
    }

    @Override
    public String dataSourceToString() throws IOException {
        InputStream is = new FileInputStream(fileName);
        return readSource(is);
    }
    @Override
    public byte[] dataSourceToBytes() throws IOException {
        return getRawContents(new File(fileName));
    }

    public static byte[] getRawContents(File f) {
        try {
            int len = (int) f.length();
            byte[] bytes = new byte[len];
            FileInputStream ins = new FileInputStream(f);
            int nread = ins.read(bytes, 0, len);
            assert nread == len;
            return bytes;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
