package med.voll.api.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import med.voll.api.domain.entities.Medico;
import med.voll.api.domain.records.MedicoAtualizacaoDTO;
import med.voll.api.domain.records.MedicoCadastroDTO;
import med.voll.api.domain.records.MedicoDTO;
import med.voll.api.domain.records.MedicoListagemDTO;
import med.voll.api.domain.repository.MedicoRepository;

@Service
public class MedicoService {

    @Autowired
    MedicoRepository medicoRepository;

    @Transactional
    public MedicoDTO cadastrar(MedicoCadastroDTO medicoCadastroDTO) {
        return new MedicoDTO(medicoRepository.save(new Medico(medicoCadastroDTO)));
    }

    @Transactional(readOnly = true)
    public Page<MedicoListagemDTO> listar(Pageable page) {
        return medicoRepository.findByAtivoTrue(page).map(MedicoListagemDTO::new);
    }

    @Transactional(readOnly = true)
    public MedicoDTO detalhar(Long id) {
        return new MedicoDTO(medicoRepository.getReferenceById(id));
    }

    @Transactional
    public MedicoDTO atualizar(MedicoAtualizacaoDTO medicoAtualizacaoDTO) {
        var medico = medicoRepository.getReferenceById(medicoAtualizacaoDTO.id());
        medico.atualizar(medicoAtualizacaoDTO);
        return new MedicoDTO(medico);
    }

    @Transactional
    public void deletar(Long id) {
        var medico = medicoRepository.getReferenceById(id);
        medico.setAtivo(false);
    }

    public boolean isAtivo(Long medicoId) {
        return medicoRepository.isAtivo(medicoId);
    }
}
