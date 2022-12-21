package com.fatechnologies.security.sidebar;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Assagou Fabrice 2022-03-06
 */
@RestController
@RequestMapping("/api/sidebar")
public class SidebarController {

    private final SidebarJpa sidebarJpa;
    private final SidebarMapper sidebarMapper;
    private final SidebarService sidebarService;

    public SidebarController(SidebarJpa sidebarJpa, SidebarService sidebarService) {
        this.sidebarJpa = sidebarJpa;
        this.sidebarService = sidebarService;
        this.sidebarMapper = SidebarMapper.INSTANCE;
    }

    @GetMapping(value = "/get-by-id/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SidebarDto> getById(@Valid @PathVariable("id") Long id) {
        var menu = sidebarJpa.findById(id).map(sidebarMapper::modelToDto);
        var dto =   sidebarService.update(menu.get());
        return  ResponseEntity.ok().body(dto);
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SidebarDto> newTenant(@Valid @RequestBody SidebarDto menu) {
        var dto =   sidebarService.update(menu);
        return  ResponseEntity.ok().body(dto);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SidebarDto> updateTenant(@Valid @RequestBody SidebarDto menu) {
        var dto =   sidebarService.update(menu);
        return  ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteTenant( @PathVariable("id")  Long id) {
        sidebarJpa.deleteById(id);
    }

    @GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SidebarDto>> getAll() {
        return ResponseEntity.accepted().body(sidebarService.getAll());
    }

    @GetMapping(value = "/get-all-principal", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SidebarDto>> getAllPrincipal() {
        return ResponseEntity.accepted().body(sidebarService.getAllPrincipal());
    }

    @GetMapping(value = "/get-all-by-accountId/{accountId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SidebarDto>> getAllByAuthorityId(@PathVariable("accountId") UUID accountId) {
        return ResponseEntity.accepted().body(sidebarService.getAllByAccountId(accountId));
    }

}
