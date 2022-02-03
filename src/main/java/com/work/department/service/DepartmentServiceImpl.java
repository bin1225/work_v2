package com.work.department.service;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.work.department.Department;
import com.work.department.mapper.DepartmentMapper;
import com.work.user.mapper.UserMapper;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentMapper departmentMapper;

	@Autowired
	private UserMapper userMapper;

	@Transactional
	public void createDepartment(Department department){
		int depth = getDepth(department.getParentId()) + 1;
		if (depth > 4) {
			throw new IllegalArgumentException("depth is bigger than 4 -> current depth : " + depth);
		}
		department.setDepth(depth);

		departmentMapper.insert(department);
		Department findOne = departmentMapper.selectOne(department.getId());
		departmentMapper.insertHistory(findOne);
	}

	public boolean checkLowerExists(Long id) {
		return departmentMapper.selectExistLower(id);
	}

	public Department findDepartment(Long id) {
		return departmentMapper.selectOne(id);
	}

	public void checkManager(String userId) {
		if (!userMapper.checkIsManager(userId)) {
			throw new IllegalStateException(userId + "is Not Manager");
		}
	}


	@Transactional
	public void removeDepartment(Long id, String userId) {
		if (checkLowerExists(id)) {
			throw new IllegalArgumentException("Lower departments are exist");
		}
		Department findOne = departmentMapper.selectOne(id);
		findOne.setAvailable("N");
		findOne.setUpdateId(userId);

		departmentMapper.delete(id,userId);
		departmentMapper.insertHistory(findOne);
	}

	public int getDepth(Long id) {
		return departmentMapper.selectDepth(id);
	}

	public Department getAllDepartmentsTree() {

		Map<Long, Department> departmentMap = departmentMapper.selectAll()
			.stream()
			.collect(Collectors.toMap(Department::getId, Function.identity()));

		Department root = makeTree(departmentMap);

		return root;
	}

	private Department makeTree(Map<Long, Department> departmentMap) {
		departmentMap.keySet().stream()
			.forEach((id) -> {
				Department currentDepartment = departmentMap.get(id);
				addChild(currentDepartment, departmentMap);
			});

		return departmentMap.values().stream()
			.filter(department -> Objects.isNull(department.getParentId()))
			.findFirst()
			.orElseThrow(() -> new IllegalStateException("Department Tree has no root"));
	}

	void addChild(Department child, Map<Long, Department> departmentMap) {
		if (Objects.isNull(child.getParentId())) {
			return;
		}

		departmentMap.get(child.getParentId())
			.addChild(child);
	}

}
