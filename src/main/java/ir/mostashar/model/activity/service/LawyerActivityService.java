package ir.mostashar.model.activity.service;

import ir.mostashar.model.activity.LawyerActivity;
import ir.mostashar.model.activity.LawyerActivityType;
import ir.mostashar.model.activity.dto.LawyerActivityForm;
import ir.mostashar.model.activity.repository.LawyerActivityRepo;
import ir.mostashar.model.doc.Doc;
import ir.mostashar.model.doc.DocType;
import ir.mostashar.model.doc.service.DocService;
import ir.mostashar.model.file.File;
import ir.mostashar.model.file.service.FileService;
import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.lawyer.service.LawyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LawyerActivityService {


    @Autowired
    LawyerActivityRepo laRepo;

    @Autowired
    LawyerService lawyerService;

    @Autowired
    FileService fileService;

    @Autowired
    DocService docService;

    public void createLawyerActivity(@Valid LawyerActivityForm laForm) {
        Optional<Lawyer> lawyer = lawyerService.findByUid(laForm.getLawyerId());
        Optional<File> file = fileService.findFileByUid(laForm.getFileId());
        Optional<Doc> doc = docService.findByUid(laForm.getDocid());
        if (lawyer.isPresent()) {
            LawyerActivity lawyerActivity = new LawyerActivity();
            lawyerActivity.setUid(UUID.randomUUID());
            lawyerActivity.setType(laForm.getType());
            lawyerActivity.setTitle(laForm.getTitle());
            lawyerActivity.setDescription(laForm.getDescription());
            lawyerActivity.setCreationDate(System.currentTimeMillis());
            lawyerActivity.setLawyer(lawyer.get());
            lawyerActivity.setFile(file.isPresent() ? file.get() : null);
            lawyerActivity.setDoc(doc.isPresent() ? doc.get() : null);
            laRepo.save(lawyerActivity);
        }
    }

    public Optional<LawyerActivity> findbyUid(String uid) {
        Optional<LawyerActivity> la = laRepo.findByUid(UUID.fromString(uid));
        if (la.isPresent())
            return Optional.ofNullable(la.get());
        else
            return Optional.empty();
    }

    public Optional<List<LawyerActivity>> findAllByTitle(String title) {
        Optional<List<LawyerActivity>> list = laRepo.findAllByTitle(title);
        if (list.isPresent())
            return Optional.ofNullable(list.get());
        else
            return Optional.empty();
    }

    public Optional<List<LawyerActivity>> findAllByLawyerUid(String lawyerUid) {
        Optional<List<LawyerActivity>> list = laRepo.findAllByLawyerUid(UUID.fromString(lawyerUid));
        if (list.isPresent())
            return Optional.ofNullable(list.get());
        else
            return Optional.empty();
    }

}
