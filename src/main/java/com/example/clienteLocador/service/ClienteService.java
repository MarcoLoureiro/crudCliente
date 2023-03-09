package com.example.clienteLocador.service;

import com.example.clienteLocador.model.Cliente;
import com.example.clienteLocador.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarClientes(){
        return this.clienteRepository.findAll();
    }

    public void adicionarCliente(Cliente cliente){
        this.clienteRepository.save(cliente);
    }

    public Optional<Cliente> buscarCliente(Long id){
        Optional<Cliente> optionalCliente = this.clienteRepository.findById(id);
        if(optionalCliente.isPresent()){
            return optionalCliente;
        }else{
            return null;
        }
    }

    public void deletarCliente(Long id){
        Optional<Cliente> optionalCliente = this.clienteRepository.findById(id);
        if(optionalCliente.isPresent()){
            this.clienteRepository.deleteById(id);
        }else{
            System.out.println("Não encontrado");
        }
    }

    public Optional<Cliente> findByCpf(int cpf){
        return this.clienteRepository.findByCpfContaining(cpf);
    }

}
