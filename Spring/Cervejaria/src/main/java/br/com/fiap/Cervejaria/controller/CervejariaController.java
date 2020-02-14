package br.com.fiap.Cervejaria.controller;

import br.com.fiap.Cervejaria.dto.CervejaDTO;
import br.com.fiap.Cervejaria.dto.CreateCervejaDTO;
import br.com.fiap.Cervejaria.dto.PrecoCervejaDTO;
import br.com.fiap.Cervejaria.dto.Tipo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cervejas")
public class CervejariaController {
    private List<CervejaDTO> cervejaDTOList;

    public CervejariaController() {
        cervejaDTOList = new ArrayList<>();

        cervejaDTOList.add(new CervejaDTO(1
                          ,"Marca 1"
                          ,4.5
                          ,Tipo.PILSEN
                          ,BigDecimal.valueOf(10.9)
                          ,ZonedDateTime.now().minusYears(3)));

        cervejaDTOList.add(new CervejaDTO(2
                ,"Marca 2"
                ,3.5
                ,Tipo.ALE
                ,BigDecimal.valueOf(6.9)
                ,ZonedDateTime.now().minusYears(2)));

        cervejaDTOList.add(new CervejaDTO(3
                ,"Marca 3"
                ,7.5
                ,Tipo.WEISS
                ,BigDecimal.valueOf(11.9)
                ,ZonedDateTime.now().minusYears(1)));
    }

    @GetMapping(value = "/cardapio")
    public List<CervejaDTO> cardapio(@RequestParam(required = false) Tipo tipo) {
        return cervejaDTOList.stream()
                .filter(cvj -> tipo == null || cvj.getTipo().equals(tipo))
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public CervejaDTO findById(@PathVariable Integer id) {
        return cervejaDTOList.stream()
                .filter(cvj -> cvj.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CervejaDTO create(@RequestBody CreateCervejaDTO createCerveja) {
        CervejaDTO cervejaDTO = new CervejaDTO(createCerveja, cervejaDTOList.size() + 1);
        cervejaDTOList.add(cervejaDTO);

        return cervejaDTO;
    }

    @PutMapping("{id}")
    public CervejaDTO update(@PathVariable Integer id, @RequestBody CreateCervejaDTO createCerveja) {
        CervejaDTO cervejaDTO = findById(id);
        cervejaDTO.setMarca(createCerveja.getMarca());
        cervejaDTO.setTeorAlcoolico(createCerveja.getTeorAlcoolico());
        cervejaDTO.setTipo(createCerveja.getTipo());
        cervejaDTO.setPreco(createCerveja.getPreco());
        cervejaDTO.setDataLancamento(createCerveja.getDataLancamento());

        return cervejaDTO;
    }

    @PatchMapping("{id}")
    public CervejaDTO update(@PathVariable Integer id, @RequestBody PrecoCervejaDTO precoCerveja) {
        CervejaDTO cervejaDTO = findById(id);
        cervejaDTO.setPreco(precoCerveja.getPreco());

        return cervejaDTO;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        CervejaDTO cervejaDTO = findById(id);
        cervejaDTOList.remove(cervejaDTO);
    }
}
