package pl.slabonart.java_adv_bc_module_4.rerository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.slabonart.java_adv_bc_module_4.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
