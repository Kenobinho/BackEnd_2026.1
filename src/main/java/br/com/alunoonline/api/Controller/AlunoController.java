package br.com.alunoonline.api.Controller;


import br.com.alunoonline.api.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Spring não sabe de nada! temos que dizer a ele que isso é um controller por meio da anotação


@RestController
@RequestMapping("/alunos") //quando acessar /alunos ele vai usar o AlunoController
public class AlunoController {
    @Autowired // serve para injetar as coisas
    AlunoService alunoService;
}
