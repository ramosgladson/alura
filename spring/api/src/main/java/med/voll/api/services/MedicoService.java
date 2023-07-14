package med.voll.api.services;


import med.voll.api.entities.Medico;
import med.voll.api.records.MedicoAtualizacaoDTO;
import med.voll.api.records.MedicoDTO;
import med.voll.api.records.MedicoListagemDTO;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MedicoService {

    @Autowired
    MedicoRepository medicoRepository;

    @Transactional
    public MedicoDTO cadastrar(Medico medico){
        medicoRepository.save(medico);
        return new MedicoDTO(medico);
    }
    @Transactional(readOnly = true)
    public Page<MedicoListagemDTO> listar(Pageable page){
        return medicoRepository.findByAtivoTrue(page).map(MedicoListagemDTO::new);
    }
    @Transactional(readOnly = true)
    public MedicoDTO detalhar(Long id){
        return new MedicoDTO(medicoRepository.getReferenceById(id));
    }
    @Transactional
    public MedicoDTO atualizar(MedicoAtualizacaoDTO medicoAtualizacaoDTO){
        var medico = medicoRepository.getReferenceById(medicoAtualizacaoDTO.id());
        medico.atualizar(medicoAtualizacaoDTO);
        return new MedicoDTO(medico);
    }
    @Transactional
    public void deletar(Long id){
        var medico = medicoRepository.getReferenceById(id);
        medico.setAtivo(false);
    }
}
