

/**
 *
 * @author Craig's
 */

//import javax.microedition.rms.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class HighestScore {

    public static void main(String[] args) {
        try {
            //
            // Create instances of FileOutputStream and ObjectOutputStream.
            //
            FileOutputStream fos = new FileOutputStream("D:\\My Documents\\NetBeansProjects\\Game\\scores.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            //
            // Create a Book instance. This book object then will be stored in
            // the file.
            //
            Book book = new Book(Game.finalscore, Game.name);

            //
            // By using writeObject() method of the ObjectOutputStream we can
            // make the book object persistent on the books.dat file.
            //
            oos.writeObject(book);

            //
            // Flush and close the ObjectOutputStream.
            //
            oos.flush();
            oos.close();

            //
            // We have the book saved. Now it is time to read it back and display
            // its detail information.
            //
            FileInputStream fis = new FileInputStream("D:\\My Documents\\NetBeansProjects\\Game\\scores.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);

            //
            // To read the Book object use the ObjectInputStream.readObject() method.
            // This method return Object type data so we need to cast it back the its
            // origin class, the Book class.
            //
            book = (Book) ois.readObject();
            System.out.println(book.toString());

            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

//
// The book object will be saved using ObjectOutputStream class and to be read
// back using ObjectInputStream class. To enable an object to be written to a
// stream we need to make the class implements the Serializable interface.
//
class Book implements Serializable {
    public int isbn;
    public String title;
 

    public Book(int isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    
    }

    public String toString() {
        return "[Highscore: " + isbn + ", " + title + "]";
    }
}