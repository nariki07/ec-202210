package com.example.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.domain.Item;

@SpringBootTest
class ItemRepositoryTest {
	
	@Autowired
	private ItemRepository itemRepository; 
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	public void testFindAll() {
		List<Item> itemList = itemRepository.findAll();
		assertEquals(18,itemList.size(),"件数が一致しません");
		assertEquals("ビニールプール",itemList.get(0).getName(),"価格が安い順に並んでいません");
		assertEquals("ミニ四駆スターターパック　MAパワータイプ（ブラストアロー）",itemList.get(17).getName(),"価格が安い順に並んでいません");
	}
	
	@Test
	public void testFindById() {
		Item item = itemRepository.findById(1);
		assertEquals("ビニールプール",item.getName(),"検索結果が一致しません。");
	}
	
	@Test
	public void findByName() {
		List<Item> itemList = itemRepository.findByName("ビニ");
		assertEquals("ビニールプール",itemList.get(0).getName(),"検索結果が一致しません。");
	}

}
