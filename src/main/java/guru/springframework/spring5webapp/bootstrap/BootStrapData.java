package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component //przekazujemy Springowi, ze jest to Spring managed component
public class BootStrapData implements CommandLineRunner {

    //2 properties: repo autorów i repo książek
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        //Spring konstruuje Beany, które automatycznie wstrzykują authorRepo i BookRepo
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Bootstrap");

        Publisher publisher = new Publisher();
        publisher.setName("Helion SA");
        publisher.setAdressLine("Kościuszki 1c");
        publisher.setCity("Gliwice");

        publisherRepository.save(publisher);
        System.out.println("Number of publishers: "+publisherRepository.count());

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);


        //"Helion SA", "Kościuszki 1c", "Gliwice",
        //                "Sląskie", "44-100");

        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);

        //To BARDZO BARDZO BARDZO ważne, żeby publisherRepo było na samym końcu, po updatcie bookRepo
        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(publisher);

        Author rod = new Author("Rod", "Johnson"); //Founder of Spring
        Book noEJB = new Book("J2EE Development without EJB", "827132");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        noEJB.setPublisher(publisher);
        publisher.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(publisher);

        System.out.println("Number of books: "+bookRepository.count());
        System.out.println("Number of books by publisher: "+publisher.getBooks().size());


        /*
        System.out.println("Publisher test");
        System.out.println("Number of publishers: "+publisherRepository.count());
        */

    }
}
