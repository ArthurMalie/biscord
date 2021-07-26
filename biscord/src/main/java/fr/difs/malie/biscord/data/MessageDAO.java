package fr.difs.malie.biscord.data;

import fr.difs.malie.biscord.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageDAO extends JpaRepository<Message, Integer> {
}
