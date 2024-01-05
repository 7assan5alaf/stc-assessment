package com.stc.filemngt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.stc.filemngt.entity.Item;
import com.stc.filemngt.entity.Permissions;
import com.stc.filemngt.error.RecordNotFoundException;
import com.stc.filemngt.repository.ItemReopsitory;

@Service
public class ItemService {

	@Autowired
	private ItemReopsitory itemReopsitory;
	@Autowired
	private PermissionService permissionService;

	@Autowired
	private PermissionGroupService groupService;

	public Item findItemById(Long itemId) {
		return itemReopsitory.findById(itemId).orElseThrow(() -> new RecordNotFoundException("Item Not Found"));
	}

	public ResponseEntity<?> createItem(String itemName, Long groupId, String parantItemId, Long permissionId) {
		Permissions permission = permissionService.findPermissionById(permissionId);
		Item item = new Item();
		if (!parantItemId.isEmpty()) {
			String perantItemName = findItemById(Long.parseLong(parantItemId)).getName();
			if (permission.getPermission_level().equals("EDIT")) {
				item.setName(perantItemName + "\\\\" + itemName);
				item.setType("Folder");
				item.setPermissionGroup(groupService.findPermissionGroupById(groupId));
				itemReopsitory.save(item);
				return new ResponseEntity<String>("Folder Added Successfully", HttpStatus.ACCEPTED);
			}
		} else {

			if (permission.getPermission_level().equals("EDIT")||
					permission.getPermission_level().equals("VIEW")) {
				item.setName(itemName);
				item.setType("Space");
				item.setPermissionGroup(groupService.findPermissionGroupById(groupId));
				itemReopsitory.save(item);
				return new ResponseEntity<String>("Space Added Successfully", HttpStatus.ACCEPTED);
			}
		}

		return new ResponseEntity<String>("Not Allowed", HttpStatus.FORBIDDEN);
	}

	public void insert(Item item) {
		itemReopsitory.save(item);
	}

}
