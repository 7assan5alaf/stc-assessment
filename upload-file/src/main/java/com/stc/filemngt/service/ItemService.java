package com.stc.filemngt.service;

import java.util.Objects;

import org.springframework.stereotype.Service;
import com.stc.filemngt.entity.Item;
import com.stc.filemngt.entity.Permissions;
import com.stc.filemngt.error.RecordNotFoundException;
import com.stc.filemngt.repository.ItemReopsitory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemService {
	private final ItemReopsitory itemReopsitory;
	private final PermissionService permissionService;
	private final PermissionGroupService groupService;

	public Item findItemById(Long itemId) {
		return itemReopsitory.findById(itemId).orElseThrow(() -> new RecordNotFoundException("Item Not Found"));
	}

	public String createSpace(String itemName, Long groupId) {

		Item item = new Item();
		item.setName(itemName);
		item.setPermissionGroup(groupService.findPermissionGroupById(groupId));
		item.setPath(itemName + ":");
		item.setType("Space");
		itemReopsitory.save(item);
		return "Space Added Successfully";
	}

	public String createFolder(String itemName, Long parentItemId, Long permissionId) {
		Permissions permissions = permissionService.findPermissionById(permissionId);
		Item parentItem = findItemById(parentItemId);
		Item item = new Item();
		if (!Objects.nonNull(parentItem)) {
			return "Item not founded";
		}

		if (!parentItem.getType().equals("File")) {
			if (parentItem.getPermissionGroup().getName().equals(permissions.getPermissionGroup().getName())
					&& permissions.getPermission_level().equals("EDIT")) {

				if (parentItem.getType().equals("Folder"))
					item.setPath(parentItem.getPath() + "\\" + parentItem.getName());
				else
					item.setPath(parentItem.getPath());

				item.setName(itemName);
				item.setPermissionGroup(parentItem.getPermissionGroup());
				item.setType("Folder");
				itemReopsitory.save(item);
				return "Add Folder Successfully";
			} else
				return "Not Allowed";
		}
		return "Can't upload folder because invalid itemId";

	}

//	public String UCanAccessItemOrNot(Long ItemId, Long permissionId) {
//		Permissions permissions = permissionService.findPermissionById(permissionId);
//		Item item = findItemById(ItemId);
//		if (item.getPermissionGroup().getName().equals(permissions.getPermissionGroup().getName())) {
//			if (permissions.getPermission_level().equals("EDIT")) {
//				return "You can access";
//			}
//
//		}
//
//		return "Not Allowed";
//
//	}

	public void insert(Item item) {
		itemReopsitory.save(item);
	}

}
