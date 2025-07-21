package es.pedrazamiguez.crowdq.user.repository.mapper;

import es.pedrazamiguez.crowdq.user.domain.entity.User;
import es.pedrazamiguez.crowdq.user.repository.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserEntityMapper {

  @Mapping(target = "userId", source = "id")
  User toDomain(UserEntity userEntity);
}
