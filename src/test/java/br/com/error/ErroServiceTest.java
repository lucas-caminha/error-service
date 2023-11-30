package br.com.error;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.error.dto.erro.DeleteErroDTO;
import br.com.error.dto.erro.ErrorDTO;
import br.com.error.dto.erro.InsertErroDTO;
import br.com.error.dto.erro.UpdateErroDTO;
import br.com.error.entity.erro.Erro;
import br.com.error.exception.NotFoundException;
import br.com.error.repository.ErroRepository;
import br.com.error.service.ErroService;

@SpringBootTest
public class ErroServiceTest {

	   @Autowired
    private ErroService erroService;

    @Autowired
    private ErroRepository erroRepositoryMock;

    @BeforeEach
    public void setUp() {
        erroRepositoryMock = mock(ErroRepository.class);
    }

    @Test
    public void testSaveErro() {
        // Arrange
        InsertErroDTO insertErroDTO = new InsertErroDTO();
        insertErroDTO.setNmErro("Nome do Erro");
        insertErroDTO.setDeErro("Descrição do Erro");
        insertErroDTO.setAutor("Autor do Erro");

        Erro erroEntity = new Erro();
        erroEntity.setNmErro(insertErroDTO.getNmErro());
        erroEntity.setDeErro(insertErroDTO.getDeErro());
        erroEntity.setAutor(insertErroDTO.getAutor());

        when(erroRepositoryMock.save(any(Erro.class))).thenReturn(erroEntity);

        // Act
        ErrorDTO result = erroService.save(insertErroDTO);

        // Assert
        assertNotNull(result);
        assertEquals(insertErroDTO.getNmErro(), result.getNmErro());
        assertEquals(insertErroDTO.getDeErro(), result.getDeErro());
        assertEquals(insertErroDTO.getAutor(), result.getAutor());
    }

    @Test
    public void testUpdateErro() {
        // Arrange
        UpdateErroDTO updateErroDTO = new UpdateErroDTO();
        updateErroDTO.setCdErro(1);
        updateErroDTO.setNmErro("Novo Nome");
        updateErroDTO.setDeErro("Nova Descrição");

        Erro erroEntity = new Erro();
        erroEntity.setCdErro(updateErroDTO.getCdErro());
        erroEntity.setNmErro(updateErroDTO.getNmErro());
        erroEntity.setDeErro(updateErroDTO.getDeErro());

        when(erroRepositoryMock.findById(anyInt())).thenReturn(Optional.of(erroEntity));
        when(erroRepositoryMock.save(any(Erro.class))).thenReturn(erroEntity);

        // Act
        ErrorDTO result = erroService.update(updateErroDTO);

        // Assert
        assertNotNull(result);
        assertEquals(updateErroDTO.getNmErro(), result.getNmErro());
        assertEquals(updateErroDTO.getDeErro(), result.getDeErro());
    }

    @Test
    public void testDeleteErro() {
        // Arrange
        DeleteErroDTO deleteErroDTO = new DeleteErroDTO();
        deleteErroDTO.setCdErro(1);

        Erro erroEntity = new Erro();
        erroEntity.setCdErro(deleteErroDTO.getCdErro());

        when(erroRepositoryMock.findById(anyInt())).thenReturn(Optional.of(erroEntity));

        // Act
        assertDoesNotThrow(() -> erroService.delete(deleteErroDTO));
    }

    @Test
    public void testDeleteErroNotFound() {
        // Arrange
        DeleteErroDTO deleteErroDTO = new DeleteErroDTO();
        deleteErroDTO.setCdErro(1);

        when(erroRepositoryMock.findById(anyInt())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> erroService.delete(deleteErroDTO));
    }

    @Test
    public void testFindErroById() {
        // Arrange
        Erro erroEntity = new Erro();
        erroEntity.setCdErro(1);

        when(erroRepositoryMock.findById(anyInt())).thenReturn(Optional.of(erroEntity));

        // Act
        Optional<Erro> result = erroService.findErroById(1);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(1, result.get().getCdErro());
    }

    @Test
    public void testFindErroByIdNotFound() {
        // Arrange
        when(erroRepositoryMock.findById(anyInt())).thenReturn(Optional.empty());

        // Act & Assert
        assertFalse(erroService.findErroById(1).isPresent());
    }

}
