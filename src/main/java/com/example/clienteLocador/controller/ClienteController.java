package com.example.clienteLocador.controller;

import com.example.clienteLocador.model.Cliente;
import com.example.clienteLocador.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clientes")
    public String getListarClientes(Model model) {
        List<Cliente> listaClientes = this.clienteService.listarClientes();
        model.addAttribute("clientes", listaClientes);
        return "clientes";
    }

    @GetMapping("/cliente/adicionar")
    public String getAdicionarCliente(Model model, Cliente cliente) {
        model.addAttribute("isEditar", Boolean.FALSE);
        model.addAttribute("cliente", cliente);
        return "adicionarClientes";
    }

    @PostMapping("/cliente/adicionar")
    public String getClienteAdicionado(@ModelAttribute("cliente") Cliente cliente) {
        Boolean isCpfRepetido = false;
        List<Cliente> listaClientes = this.clienteService.listarClientes();
        for (int i = 0; i < listaClientes.size();i++){
            if(listaClientes.get(i).getCpf() == cliente.getCpf()){
                isCpfRepetido = true;
            }
        }
            if(isCpfRepetido) {
                System.err.println("CPF já cadastrado");
            }else{
                this.clienteService.adicionarCliente(cliente);
            }
        return "redirect:/clientes";
    }

    @GetMapping("/cliente/{clienteId}/deletar")
    public String deletarCliente(@PathVariable Long clienteId) {
        Optional<Cliente> clienteParaDeletar = this.clienteService.buscarCliente(clienteId);
        if (clienteParaDeletar.isPresent()) {
            this.clienteService.deletarCliente(clienteId);
        } else {
            System.out.println("Não encontrado");
        }
        return "redirect:/clientes";
    }

    @GetMapping("/cliente/{clienteId}/editar")
    public String editarCliente(Model model, @PathVariable Long clienteId) {
        Optional<Cliente> optionalCliente = this.clienteService.buscarCliente(clienteId);
        optionalCliente.ifPresent(cliente -> model.addAttribute("cliente", cliente));
        model.addAttribute("isEditar", Boolean.TRUE);
        return "adicionarClientes";
    }

    @PutMapping("/cliente/{clienteId}/editar")
    public String finalizarEdicao(@ModelAttribute("cliente") Cliente cliente, @PathVariable("clienteId") Long clienteId) {
        cliente.setId(clienteId);
        this.clienteService.adicionarCliente(cliente);
        return "redirect:/clientes";
    }
}
