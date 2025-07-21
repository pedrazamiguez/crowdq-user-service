package es.pedrazamiguez.crowdq.user.repository.entity;

import java.util.UUID;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("users")
public class UserEntity {

  @Id private UUID id;

  @Column private String username;

  @Column private String email;
}
