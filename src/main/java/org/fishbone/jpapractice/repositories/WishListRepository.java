package org.fishbone.jpapractice.repositories;

import java.util.List;
import org.fishbone.jpapractice.models.Book;
import org.fishbone.jpapractice.models.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListRepository extends JpaRepository<Wishlist, Integer> {

    List<Wishlist> findWishlistsByPersonId(int id);

    void deleteWishlistByBook_IdAndPerson_Id(int bookId, int personId);
    void deleteWishlistByBook_Id(int bookId);

}
