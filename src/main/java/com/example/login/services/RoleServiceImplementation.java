//package com.example.login.services;
//
//import com.example.dto.RolesDto;
//import com.example.login.repository.RolesRepository;
//
//import java.util.List;
//
//public class RoleServiceImplementation implements RoleService{
//    @Override
//    public RolesDto createRole(RolesDto rolesDto) {
//        return convertRoleToDto(
//                RolesRepository.save(convertDtoToRoles(rolesDto))
//        );
//    }
//
//    @Override
//    public List<RolesDto> getAllRoles() {
//        return List.of();
//    }
//
//    @Override
//    public RolesDto getRoleById(Long id) {
//        return null;
//    }
//}
