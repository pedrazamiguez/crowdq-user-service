package es.pedrazamiguez.crowdq.user.apirest.mapper;

import es.pedrazamiguez.crowdq.user.domain.entity.User;
import es.pedrazamiguez.crowdq.user.openapi.model.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserRestMapper {

  @Mapping(target = "id", source = "userId")
  @Mapping(target = "fullName", ignore = true)
  UserDto toDto(User user);
}
