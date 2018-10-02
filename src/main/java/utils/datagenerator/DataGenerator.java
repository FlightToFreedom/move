package utils.datagenerator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import interfaces.IterableLine;

/**
 * Abstract: creates a file in file system containing N amount of random words.
 * 
 * @author eiorio
 *
 */
public class DataGenerator
{

  private final String filesPath  = "/Users/eiorio/workspace/Move/src/main/java/utils/data/logfiles/data.csv";
  private int          charsStart = 65;
  private int          charsEnd   = 90;

  /**
   * Takes care io generating the data, by creating the file and filling it up
   * 
   * @return is the data generated?
   */
  public boolean generateData(int rows)
  {
    try
    {
      createFile();
    }
    catch (IOException e)
    {
      // XXX File creation failed, print!
      e.printStackTrace();
    }
    if (Files.exists(Paths.get(filesPath)) && Files.isWritable(Paths.get(filesPath)))
    {
      for (int i = 0; i < rows; i++)
      {
        String line = generateRow(10, 10, ",");   
        try
        {
          Files.write(Paths.get(filesPath), line.getBytes(), StandardOpenOption.APPEND);
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
      }
    }
    return false;
  }

  /**
   * This creates the file, at this point is very important to propagate the
   * exception properly in case the IO behaves stupidly, thats why we do not
   * handle it, caller method will take care of this,
   */
  private void createFile() throws IOException
  {
    File file = new File(filesPath);
    if (file.exists())
      file.delete();
    file.createNewFile();
  }

  /**
   * Creates a
   * 
   * @param words
   * @return
   */
  private String generateRow(int words, int wordLength, String separator)
  {
    List<String> wordsList = new ArrayList<String>(words);

    for (int i = 0; i < words; i++)
    {
      String word = generateRandomWord(wordLength);
      wordsList.add(word);
    }
    return StringUtils.join(wordsList, separator) + "\n";
  }

  /**
   * Generates a random word of {length} chars.
   * 
   * @param length
   * @return
   */
  private String generateRandomWord(int length)
  {
    StringBuilder sb = new StringBuilder(length);
    for (int i = 0; i < (length); i++)
    {
      char c;
      if (i % 2 == 0)
      {
        charsStart = 65;
        charsEnd = 90;
      }
      else
      {
        charsStart = 48;
        charsEnd = 57;
      }
      c = (char) (charsStart + (Math.random() * (charsEnd - charsStart)));
      sb.append(c);
    }
    return sb.toString();
  }

  public static void main(String... args)
  {
    DataGenerator dataGenerator = new DataGenerator();
    dataGenerator.generateData(100);
  }
}
