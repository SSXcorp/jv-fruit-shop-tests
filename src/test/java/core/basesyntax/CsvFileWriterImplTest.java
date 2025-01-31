package core.basesyntax;

import core.basesyntax.service.CsvFileReader;
import core.basesyntax.service.CsvFileWriter;
import core.basesyntax.service.impl.CsvFileReaderImpl;
import core.basesyntax.service.impl.CsvFileWriterImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CsvFileWriterImplTest {

    private static final String PATH_TO_FILE = "src/main/resources/testFiles/fileWriterTest.csv";
    private static final String REPORT = "fruit,quantity"
            + System.lineSeparator()
            + "banana,152"
            + System.lineSeparator()
            + "apple,90";
    private final CsvFileWriter fileWriter = new CsvFileWriterImpl();
    private final CsvFileReader fileReader = new CsvFileReaderImpl();

    @Test
    public void write_validData_Ok() {
        fileWriter.write(REPORT, PATH_TO_FILE);
        String expectedAfterRead = "[fruit,quantity, banana,152, apple,90]";
        Assertions.assertEquals(expectedAfterRead, fileReader.read(PATH_TO_FILE).toString());
    }

    @Test
    public void write_nullFilename_Ok() {
        Assertions.assertThrows(RuntimeException.class, () -> fileWriter.write(REPORT, null));
    }
}
