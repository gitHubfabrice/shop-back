package com.fatechnologies.security.sidebar;

import com.fatechnologies.security.adapter.repository.jpa.RoleJpa;
import com.fatechnologies.security.adapter.repository.jpa.UserJpa;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class SidebarServiceImpl implements SidebarService {


    private final SidebarJpa sidebarJpa;
    private final SidebarMapper sidebarMapper;
    private final UserJpa userJpa;
    private final RoleJpa roleJpa;

    public SidebarServiceImpl(SidebarJpa sidebarJpa, UserJpa userJpa, RoleJpa roleJpa) {
        this.sidebarJpa = sidebarJpa;
        this.userJpa = userJpa;
        this.roleJpa = roleJpa;
        this.sidebarMapper = SidebarMapper.INSTANCE;
    }

    @Override
    public SidebarDto create(SidebarDto dto) {
        var menu = sidebarMapper.dtoToModel(dto);
        return sidebarMapper.modelToDto(sidebarJpa.save(menu));
    }

    @Override
    public SidebarDto update(SidebarDto dto) {
        var menu = sidebarMapper.dtoToModel(dto);
        return sidebarMapper.modelToDto(sidebarJpa.save(menu));
    }

    @Override
    public List<SidebarDto> getAllPrincipal() {
        var menus = sidebarJpa.findAll();
        var menuDtos = new ArrayList<SidebarDto>();
        for (Sidebar entity: menus){

         var childs = new ArrayList<SidebarDto>();
         var dto = sidebarMapper.modelToDto(entity);

            if (!entity.getChilds().isEmpty()){
                for (Sidebar child : entity.getChilds()) {
                    var item = sidebarJpa.findById(child.getId());
                    item.ifPresent(menu -> childs.add(sidebarMapper.modelToDto(menu)));
                }
                childs.sort(SidebarDto.ComparatorOrder);
                dto.getChilds().clear();
                dto.getChilds().addAll(childs);
            }
            if(sidebarJpa.checkIfChild(dto.getId()).isEmpty()){
                menuDtos.add(dto);
            }

        }
        menuDtos.sort(SidebarDto.ComparatorOrder);

        return menuDtos;
    }

    @Override
    public List<SidebarDto> getAll() {
        var menus = sidebarJpa.findAll();
        var menuDtos = new ArrayList<SidebarDto>();

        for (Sidebar entity: menus){
            var dto = sidebarMapper.modelToDto(entity);
            menuDtos.add(dto);
        }

        Collections.sort(menuDtos, SidebarDto.ComparatorOrder);

        return menuDtos;
    }

    @Override
    public List<SidebarDto> getAllByAccountId(UUID accountId) {

        var account = userJpa.findById(accountId).orElseThrow();
        var role = roleJpa.findById(account.getRole().getId()).orElseThrow();
        var menusAccount = new ArrayList<SidebarDto>();
        role.getAuthorities().forEach(authority -> {
            var entities = sidebarJpa.findAllByAuthotityId(authority.getId());
            if (!entities.isEmpty()) {

                entities.forEach(entity -> {
                    var childs = new ArrayList<SidebarDto>();
                    var dto = sidebarMapper.modelToDto(entity);

                    if (!entity.getChilds().isEmpty()){
                        for (Sidebar child : entity.getChilds()) {
                            var item = sidebarJpa.findById(child.getId());
                            item.ifPresent(menu -> childs.add(sidebarMapper.modelToDto(menu)));
                        }
                        childs.sort(SidebarDto.ComparatorOrder);
                        dto.getChilds().clear();
                        dto.getChilds().addAll(childs);
                    }
                    if(sidebarJpa.checkIfChild(entity.getId()).isEmpty()){
                        menusAccount.add(dto);
                    }
                });
            }
        });
        menusAccount.sort(SidebarDto.ComparatorOrder);
        //return sidebarMapper.modelsToDtos(menus);
        return menusAccount;
    }
}
