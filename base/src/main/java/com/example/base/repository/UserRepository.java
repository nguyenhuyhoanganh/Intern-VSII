package com.example.base.repository;

import com.example.base.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsById(Long id);

    Optional<User> findByUsername(String username);

    @Query(value = "call sp_findAllUser()",nativeQuery = true)
    List<User> sp_findAllUser();

    @Query(value = "call sp_findUserById(:id_in)",nativeQuery = true)
    Optional<User> sp_findUserById(@Param("id_in")Long id);


    @Procedure(procedureName = "sp_createUser")
    Optional<User> sp_createUser(@Param("date_of_birth")LocalDate dateOfBirth,
                       @Param("authentication_code") String authenticationCode,
                       @Param("email") String email,
                       @Param("first_name") String firstName,
                       @Param("last_name")String lastName,
                       @Param("phone_number")String phoneNumber,
                       @Param("username")String username);

    @Procedure(procedureName = "sp_updateUser")
    Optional<User> sp_updateUser(@Param("id_in")Long id,
                                 @Param("date_of_birth")LocalDate dateOfBirth,
                                 @Param("authentication_code") String authenticationCode,
                                 @Param("email") String email,
                                 @Param("first_name") String firstName,
                                 @Param("last_name")String lastName,
                                 @Param("phone_number")String phoneNumber,
                                 @Param("username")String username);

    @Procedure(procedureName = "sp_deleteUserById")
    void sp_deleteUserById(@Param("id_in")Long id);
}
