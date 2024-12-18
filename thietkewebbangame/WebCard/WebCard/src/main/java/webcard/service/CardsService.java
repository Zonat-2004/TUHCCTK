package webcard.service;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import webcard.model.Cards;
import webcard.model.CardsRepository;

@Service
public class CardsService {

    @Autowired
    private CardsRepository cardsRepository;

    @SuppressWarnings("null")
    public void updateImage(Cards Cards) {
        if (Cards.getImageFile() != null && !Cards.getImageFile().isEmpty()) {
            String fileName = StringUtils.cleanPath(Cards.getImageFile().getOriginalFilename());
            if (fileName.contains("..")) {
                System.out.println("Lỗi không thể lưu file");
            }
            try {
                Cards.setImage_url(Base64.getEncoder().encodeToString(Cards.getImageFile().getBytes()));
            } catch (IOException e) {

            }

        } else {
            Cards existingCourse = cardsRepository.findById(Cards.getId()).orElse(null);
            if (existingCourse != null) {
                Cards.setImage_url(existingCourse.getImage_url());
            }
        }
        cardsRepository.save(Cards);
    }
}
