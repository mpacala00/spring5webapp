package guru.springframework.spring5webapp.repositories;

import guru.springframework.spring5webapp.domain.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    /*Przyjmuje 2 typy generyczne: 1. to typ 2. to ID value
     * Musimy zadeklarować jedynie interfejs, Spring JPA zainplementuje
     * za nas metody itd.
     * takie jak: save(), która zapisuje obiekty Author do AuthorRepository
     */
}
