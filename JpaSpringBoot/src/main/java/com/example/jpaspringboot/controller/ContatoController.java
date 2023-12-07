package com.example.jpaspringboot.controller;

import com.example.jpaspringboot.dto.ContatoDTO;
import com.example.jpaspringboot.dto.ContatoRecord;
import com.example.jpaspringboot.model.Contato;
import com.example.jpaspringboot.service.ContatoService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;
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
    public ResponseEntity<Page<ContatoDTO>> listarPaginado(@PageableDefault(sort = "nome",direction = Sort.Direction.ASC) Pageable pageable){
//        PageRequest.of(pageable.getPageNumber() , pageable.getPageSize() , Sort.by("nome").ascending().and(Sort.by("id").descending()));
        Page<Contato> pagina = contatoService.listarPaginado( pageable );

        if(!pagina.isEmpty()){
            return ResponseEntity.ok( converterPageDTO(pagina) );
        }
        return ResponseEntity.noContent().build();
    }

    private Page<ContatoDTO> converterPageDTO(Page<Contato> pagina) {
        Page<ContatoDTO> pgDTO = pagina.map(ContatoDTO::convertToDTO);
        pgDTO.stream().forEach(this::adicionarLinks);
        return pgDTO;
    }

    private void adicionarLinks(ContatoDTO contatoDTO) {

//        contatoDTO.add(Link.of("/contato/{idContato}" , LinkRelation.of("consultar")).expand(contatoDTO.getId()));
// 1 das formas de ajustar o hateoas
        contatoDTO.add(Link.of(
                Objects.requireNonNull(ServletUriComponentsBuilder.fromCurrentContextPath().path("/contato").path("/{idContato}").buildAndExpand(contatoDTO.getId()).toUri().toASCIIString()),
                LinkRelation.of("consultar")));

//        contatoDTO.add(Link.of("/contato/{idContato}" , LinkRelation.of("excluir")).expand(contatoDTO.getId()));
        contatoDTO.add( WebMvcLinkBuilder.linkTo(ContatoController.class ).slash(contatoDTO.getId()).withRel( "excluir") );
// Obter link pelo nome do método
//        contatoDTO.add(Link.of("/contato/incluir" , LinkRelation.of("incluir")));

        contatoDTO.add( WebMvcLinkBuilder.linkTo( WebMvcLinkBuilder.methodOn(ContatoController.class).incluir(contatoDTO , null)  ).withRel( "incluir") );


//        contatoDTO.add(Link.of("/contato/alterar" , LinkRelation.of("alterar")));
        contatoDTO.add( WebMvcLinkBuilder.linkTo(ContatoController.class ).slash("alterar").withRel( "alterar") );
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
    public ResponseEntity<ContatoRecord> incluir(@Valid @RequestBody ContatoRecord record , HttpServletResponse response ){
        Optional<Contato> opContato = contatoService.createContato( record );
        adicionandoLocation(response, opContato);
        return opContato.map(contato -> ResponseEntity.ok(ContatoRecord.convertToRecord(contato)))
                .orElseGet(() -> ResponseEntity.noContent().build());
    }



    @PostMapping("/incluirdto")
    public ResponseEntity<ContatoRecord> incluir(@Valid @RequestBody ContatoDTO record , HttpServletResponse response ){
        Optional<Contato> opContato = contatoService.createContato(
                new ContatoRecord( null, record.getNome(),record.getSobrenome(),record.getEmail(),record.getCelular(),record.getDataNascimento() ) );
        adicionandoLocation(response, opContato);
        return opContato.map(contato -> ResponseEntity.ok(ContatoRecord.convertToRecord(contato)))
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PutMapping("/alterar")
    public ResponseEntity<ContatoRecord> alterar(@Valid @RequestBody ContatoRecord record, HttpServletResponse response){
        Optional<Contato> opContato = contatoService.updateContato( record );
        adicionandoLocation(response, opContato);
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

    /* -------------------------------------------------------------------------------- */
    private static void adicionandoLocation(HttpServletResponse response, Optional<Contato> opContato) {
        opContato.ifPresent(contato -> adicionandoLocation(response, contato));
    }

    private static void adicionandoLocation(HttpServletResponse response, Contato contato) {
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("contato").path("/{idContato}").buildAndExpand(contato.getId()).toUri();
        response.setHeader("location", uri.toASCIIString());
    }

}
