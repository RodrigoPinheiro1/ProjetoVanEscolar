package mocks;

import com.van.vanescolarprojeto.dto.*;
import com.van.vanescolarprojeto.modelo.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MockObjects {


    public static AutomovelDto automovelDto() {

        return new AutomovelDto(1L, "asdasd", "asdasd", "asdasd");
    }

    public static MotoristaAutomovelDto motoristaAutomovelDto() {

        return new MotoristaAutomovelDto(1L, "sad", "cpf", "cof", endereco(),
                "asdasd", Date.from(Instant.now()), automovelDto());
    }


    public static Page<MotoristaDto> motoristaDtoPage() {

        MotoristaDto motoristaDto = new MotoristaDto();

        List<MotoristaDto> lista = List.of(motoristaDto);

        return new PageImpl<>(lista, PageRequest.of(0, lista.size()), lista.size());

    }

    public static Page<ResponsavelDto> responsavelDtoPage() {

        ResponsavelDto responsavelDto = new ResponsavelDto(1L, "sado", Date.from(Instant.now()), "soadk", "soadk", endereco(),
                StatusPedidoCorrida.Feito_Pedido, EstadoCivil.CASADO, alunoDtos());

        List<ResponsavelDto> lista = List.of(responsavelDto);

        // Você pode personalizar o PageRequest com número da página e tamanho
        return new PageImpl<>(lista, PageRequest.of(0, lista.size()), lista.size());
    }

    public static List<AlunoDto> alunoDtos() {

        AlunoDto alunoDto = new AlunoDto(1L, "saodkasd", LocalDate.now(), "soakdas", "oaskdokasd");

        return Collections.singletonList(alunoDto);
    }

    public static Endereco endereco() {

        return new Endereco("sdasd", "sadasd", "asdasd", "asdasd", "asdasd", "asdasd", "asdasd");
    }

    public static Motorista motorista() {
        Motorista motorista = new Motorista();
        motorista.setId(1L);
        motorista.setNome("João");
        motorista.setCnh("123456");
        motorista.setTelefone("11999999999");
        motorista.setEndereco(endereco());
        motorista.setStatusPedidoCorrida(null); // será definido no teste
        return motorista;
    }

    public static Responsavel responsavel() {
        Responsavel responsavel = new Responsavel();
        responsavel.setId(10L);
        responsavel.setNome("Maria");
        responsavel.setDataNascimento(Date.from(Instant.now()));
        responsavel.setEndereco(endereco());
        responsavel.setStatusPedidoCorrida(null); // será definido no teste
        responsavel.setEstadoCivil(EstadoCivil.CASADO);
        return responsavel;
    }


}
