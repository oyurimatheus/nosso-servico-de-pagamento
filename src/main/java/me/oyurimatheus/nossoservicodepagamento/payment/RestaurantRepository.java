package me.oyurimatheus.nossoservicodepagamento.payment;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import javax.validation.constraints.Email;
import java.util.Set;

interface RestaurantRepository extends Repository<Restaurant, Long> {

    /**
     *
     * @param restaurantId the restaurant which client request the payment methods
     * @param user the user who is requesting the payment methods
     * @return a set of {@link PaymentMethod} which both client and restaurant accept
     */
    @Query(value = "SELECT DISTINCT rp.payment_method FROM RESTAURANTS_PAYMENT_METHODS rp " +
            "INNER JOIN user_payment_methods up " +
            "INNER JOIN users u " +
            "ON (up.payment_method = rp.payment_method) AND (up.user_id = u.user_id) " +
            "WHERE rp.restaurant_id = :restaurantId AND u.email = :user",
    nativeQuery = true)
    Set<PaymentMethod> findPaymentMethodsAllowedTo(Long restaurantId, @Email String user);


}
