package com.example.transfermoney.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;

@SpringBootTest
@AutoConfigureMockMvc
public class TransferServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void transferMoneyValidTest() throws Exception{
        mockMvc.perform(multipart("/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"cardFromNumber\" : \"1111222233335555\",\n" +
                                "    \"cardFromValidTill\" : \"04/24\",\n" +
                                "    \"cardFromCVV\" : \"123\",\n" +
                                "    \"cardToNumber\" : \"1111222233334444\",\n" +
                                "    \"amount\" : {\n" +
                                "        \"value\" : 1000,\n" +
                                "        \"currency\" : \"Рубль\"\n" +
                                "    }\n" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void transferMoneyNotValidCardForNumTest() throws Exception{
        mockMvc.perform(multipart("/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"cardFromNumber\" : \"11112222333355559\",\n" +
                                "    \"cardFromValidTill\" : \"04/24\",\n" +
                                "    \"cardFromCVV\" : \"123\",\n" +
                                "    \"cardToNumber\" : \"1111222233334444\",\n" +
                                "    \"amount\" : {\n" +
                                "        \"value\" : 1000,\n" +
                                "        \"currency\" : \"Рубль\"\n" +
                                "    }\n" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json("{\n" +
                        "    \"id\": null,\n" +
                        "    \"message\": \"Номер карты отправителя дожен состоять из 16 цифр\"\n" +
                        "}"));;
    }
    @Test
    public void transferMoneyNotCardForNumEmptyTest() throws Exception{
        mockMvc.perform(multipart("/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"cardFromValidTill\" : \"04/24\",\n" +
                                "    \"cardFromCVV\" : \"123\",\n" +
                                "    \"cardToNumber\" : \"1111222233334444\",\n" +
                                "    \"amount\" : {\n" +
                                "        \"value\" : 1000,\n" +
                                "        \"currency\" : \"Рубль\"\n" +
                                "    }\n" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json("{\n" +
                        "    \"id\": null,\n" +
                        "    \"message\": \"Номер карты отправителя не указан\"\n" +
                        "}"));;
    }
    @Test
    public void transferMoneyNotCorrectCvvTest() throws Exception{
        mockMvc.perform(multipart("/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"cardFromNumber\" : \"1111222233335555\",\n" +
                                "    \"cardFromValidTill\" : \"04/24\",\n" +
                                "    \"cardFromCVV\" : \"1234\",\n" +
                                "    \"cardToNumber\" : \"1111222233334444\",\n" +
                                "    \"amount\" : {\n" +
                                "        \"value\" : 1000,\n" +
                                "        \"currency\" : \"Рубль\"\n" +
                                "    }\n" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json("{\n" +
                        "    \"id\": null,\n" +
                        "    \"message\": \"ccv должен состоять из 3 цифр\"\n" +
                        "}"));;
    }
    @Test
    public void transferMoneyNotValidCvvTest() throws Exception{
        mockMvc.perform(multipart("/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"cardFromNumber\" : \"1111222233335555\",\n" +
                                "    \"cardFromValidTill\" : \"04/24\",\n" +
                                "    \"cardFromCVV\" : \"127\",\n" +
                                "    \"cardToNumber\" : \"1111222233334444\",\n" +
                                "    \"amount\" : {\n" +
                                "        \"value\" : 1000,\n" +
                                "        \"currency\" : \"Рубль\"\n" +
                                "    }\n" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json("{\n" +
                        "    \"id\": null,\n" +
                        "    \"message\": \"Некорректный CVV код\"\n" +
                        "}"));;
    }
    @Test
    public void transferMoneyCvvEmptyTest() throws Exception{
        mockMvc.perform(multipart("/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"cardFromNumber\" : \"1111222233335555\",\n" +
                                "    \"cardFromValidTill\" : \"04/24\",\n" +
                                "    \"cardToNumber\" : \"1111222233334444\",\n" +
                                "    \"amount\" : {\n" +
                                "        \"value\" : 1000,\n" +
                                "        \"currency\" : \"Рубль\"\n" +
                                "    }\n" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json("{\n" +
                        "    \"id\": null,\n" +
                        "    \"message\": \"Не указан некорректный cvv\"\n" +
                        "}"));
    }

    @Test
    public void transferMoneyNotValidNumCardToTest() throws Exception{
        mockMvc.perform(multipart("/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"cardFromNumber\" : \"1111222233335555\",\n" +
                                "    \"cardFromValidTill\" : \"04/24\",\n" +
                                "    \"cardFromCVV\" : \"1234\",\n" +
                                "    \"cardToNumber\" : \"11112222333344445\",\n" +
                                "    \"amount\" : {\n" +
                                "        \"value\" : 1000,\n" +
                                "        \"currency\" : \"Рубль\"\n" +
                                "    }\n" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json("{\n" +
                        "    \"id\": null,\n" +
                        "    \"message\": \"Номер карты получателя дожен состоять из 16 цифр\"\n" +
                        "}"));
    }
    @Test
    public void transferMoneyEmptyNumCardToTest() throws Exception{
        mockMvc.perform(multipart("/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"cardFromNumber\" : \"1111222233335555\",\n" +
                                "    \"cardFromValidTill\" : \"04/24\",\n" +
                                "    \"cardFromCVV\" : \"127\",\n" +
                                "    \"amount\" : {\n" +
                                "        \"value\" : 1000,\n" +
                                "        \"currency\" : \"Рубль\"\n" +
                                "    }\n" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json("{\n" +
                        "    \"id\": null,\n" +
                        "    \"message\": \"Не указан номер карты получателя\"\n" +
                        "}"));;
    }
    @Test
    public void transferMoneyNumCardToNotExistTest() throws Exception{
        mockMvc.perform(multipart("/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"cardFromNumber\" : \"1111222233335555\",\n" +
                                "    \"cardFromValidTill\" : \"04/24\",\n" +
                                "    \"cardToNumber\" : \"1111222233334441\",\n" +
                                "    \"amount\" : {\n" +
                                "        \"value\" : 1000,\n" +
                                "        \"currency\" : \"Рубль\"\n" +
                                "    }\n" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json("{\n" +
                        "    \"id\": null,\n" +
                        "    \"message\": \"Карты с номером [1111222233334441] не существует\"\n" +
                        "}"));
    }
    @Test
    public void transferMoneyAmountEmpty() throws Exception{
        mockMvc.perform(multipart("/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"cardFromNumber\" : \"1111222233335555\",\n" +
                                "    \"cardFromValidTill\" : \"04/24\",\n" +
                                "    \"cardFromCVV\" : \"127\",\n" +
                                "    \"cardToNumber\" : \"1111222233334441\"" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json("{\n" +
                        "    \"id\": null,\n" +
                        "    \"message\": \"Данные по сумме и валюте не заданы\"\n" +
                        "}"));;
    }
}
