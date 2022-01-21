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

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentMapper departmentMapper;

	@Transactional
	public Long createDepartment(Department department) throws IllegalArgumentException {
		int depth = selectDepth(department.getParentId())+1;
		if (depth > 4) {
			throw new IllegalArgumentException("depth가 4보다 큽니다.");
		}
		department.setDepth(depth);
		departmentMapper.insert(department);
		return department.getId();
	}

	public boolean checkLowerExists(Long id) {
		return departmentMapper.selectExistLower(id);
	}

	public Department findDepartment(Long id) {
		return departmentMapper.selectOne(id);
	}

	@Transactional
	public void removeDepartment(Long id) throws IllegalArgumentException {
		if (checkLowerExists(id)) {
			throw new IllegalArgumentException();
		}
		departmentMapper.delete(id);
	}

	public int selectDepth(Long id) {
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
