package br.com.fiap.kotlinwebflux.controller

import br.com.fiap.kotlinwebflux.dto.PessoaDTO
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.time.Duration

@RestController
class PessoaController {

    @GetMapping
    fun getPessoas(): Flux<PessoaDTO> = Flux.just(arrayListOf(
            PessoaDTO(id = 1, cpf = "123", nome = "Fabio"),
            PessoaDTO(id = 2, cpf = "456", nome = "José"),
            PessoaDTO(id = 3, cpf = "789", nome = "Maria")))
            .flatMapIterable { it }
            .delayElements(Duration.ofSeconds(2))

    @GetMapping
    fun getPessoasDefault(): Flux<PessoaDTO> = Flux.create {
        it.next(PessoaDTO(id = 1, cpf = "123", nome = "Fabio"))
        it.next(PessoaDTO(id = 2, cpf = "456", nome = "Jose"))
        it.next(PessoaDTO(id = 3, cpf = "789", nome = "Maria"))
        it.complete()
    }
}