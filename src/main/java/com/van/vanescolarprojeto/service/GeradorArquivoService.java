package com.van.vanescolarprojeto.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.van.vanescolarprojeto.exceptions.ArquivoException;
import com.van.vanescolarprojeto.modelo.Motorista;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class GeradorArquivoService {


    private final ObjectMapper objectMapper;


    public void salvarMudancaEmArquivo(Motorista antigo, Motorista novo) {
        try {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String nomeArquivo = "alteracoes_motorista_" + antigo.getId() + "_" + timestamp + ".json";

            Map<String, Object> registro = new HashMap<>();
            registro.put("id", antigo.getId());
            registro.put("antes", antigo);
            registro.put("depois", novo);

            objectMapper.writeValue(new File("logs/" + nomeArquivo), registro);


        } catch (IOException e) {
            log.info("erro ao criar arquivo {} ",e.getMessage());
            throw new ArquivoException();
        }
    }


    public void salvarMotoristaEmArquivo(Motorista motorista) {
        try {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String nomeArquivo = "motorista_cadastrado_" + motorista.getId() + "_" + timestamp + ".json";

            Files.createDirectories(Paths.get("logs"));
            objectMapper.writeValue(new File("logs/" + nomeArquivo), motorista);

        } catch (IOException e) {
            log.info("erro ao atualizar arquivo{} ",e.getMessage());
            throw new ArquivoException();
        }
    }


}
