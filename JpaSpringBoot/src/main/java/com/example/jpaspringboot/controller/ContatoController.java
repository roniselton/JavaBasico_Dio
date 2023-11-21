package com.example.jpaspringboot.controller;

import com.example.jpaspringboot.dto.ContatoDTO;
import com.example.jpaspringboot.dto.ContatoRecord;
import com.example.jpaspringboot.model.Contato;
import com.example.jpaspringboot.service.ContatoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/contato")
public class ContatoController {

    private static final Logger logger = Logger.getLogger(ContatoController.class.getName());

    private final ContatoService contatoService;

    public ContatoController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    @GetMapping
    public ResponseEntity<Page<ContatoRecord>> listarPaginado(@PageableDefault(sort = "nome",direction = Sort.Direction.ASC) Pageable pageable){
//        PageRequest.of(pageable.getPageNumber() , pageable.getPageSize() , Sort.by("nome").ascending().and(Sort.by("id").descending()));
        Page<Contato> pagina = contatoService.listarPaginado( pageable );
        if(!pagina.isEmpty()){
            return ResponseEntity.ok( pagina.map(ContatoRecord::convertToRecord));
        }
        return ResponseEntity.noContent().build();
    }

    /* O request param torna obrigatorio um param ... colocar o required ou valor default para remover a obrigatoriedade */
    @GetMapping("/listar")
    public ResponseEntity<List<ContatoRecord>> listarTodos(@RequestParam(value = "size", required = false) Integer size){
        List<Contato> lista = contatoService.listarTodos();
        if(!CollectionUtils.isEmpty( lista )){
            if(size == null)
                size = lista.size();
            return ResponseEntity.ok( lista.parallelStream().limit(size)
                    .map(ContatoRecord::convertToRecord).collect(Collectors.toList()));
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/incluir")
    public ResponseEntity<ContatoRecord> incluir(@Valid @RequestBody ContatoRecord record){
        Optional<Contato> opContato = contatoService.createContato( record );
        return opContato.map(contato -> ResponseEntity.ok(ContatoRecord.convertToRecord(contato)))
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping("/incluirdto")
    public ResponseEntity<ContatoRecord> incluir(@Valid @RequestBody ContatoDTO record){
        Optional<Contato> opContato = contatoService.createContato(
                new ContatoRecord( null, record.getNome(),record.getSobrenome(),record.getEmail(),record.getCelular(),record.getDataNascimento() ) );
        return opContato.map(contato -> ResponseEntity.ok(ContatoRecord.convertToRecord(contato)))
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping("/alterar")
    public ResponseEntity<ContatoRecord> alterar(@Valid @RequestBody ContatoRecord record){
        Optional<Contato> opContato = contatoService.updateContato( record );
        return opContato.map(contato -> ResponseEntity.ok(ContatoRecord.convertToRecord(contato)))
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping("/{idContato}")
    public ResponseEntity<ContatoRecord> detalhar(@PathVariable("idContato") Integer idContato){
        Optional<Contato> opContato = contatoService.findById( idContato  );
        return opContato.map(contato -> ResponseEntity.ok(ContatoRecord.convertToRecord(contato)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{idContato}")
    public ResponseEntity<ContatoRecord> excluir(@PathVariable("idContato") Integer idContato){
        Optional<Contato> opContato = contatoService.excluir( idContato  );
        return opContato.map(contato -> ResponseEntity.ok(ContatoRecord.convertToRecord(contato)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
