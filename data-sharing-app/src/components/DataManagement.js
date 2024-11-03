import React, { useState, useEffect } from 'react';
import { Table, Button, Modal, Input, message } from 'antd';
import { createData, getData, updateData, deleteData } from '../api/data';

const DataManagement = () => {
    const [dataList, setDataList] = useState([]);
    const [isModalVisible, setIsModalVisible] = useState(false);
    const [currentData, setCurrentData] = useState(null);
    const [newDataContent, setNewDataContent] = useState('');

    const fetchData = async () => {
        const response = await getData();
        setDataList(response.data);
    };

    useEffect(() => {
        fetchData();
    }, []);

    const handleAddData = async () => {
        try {
            await createData(newDataContent);
            message.success('数据上传成功');
            fetchData();
            setIsModalVisible(false);
        } catch (error) {
            message.error('上传失败');
        }
    };

    const handleEditData = async (id) => {
        try {
            await updateData(id, newDataContent);
            message.success('数据修改成功');
            fetchData();
            setIsModalVisible(false);
        } catch (error) {
            message.error('修改失败');
        }
    };

    const handleDeleteData = async (id) => {
        try {
            await deleteData(id);
            message.success('数据删除成功');
            fetchData();
        } catch (error) {
            message.error('删除失败');
        }
    };

    return (
        <div>
            <Button type="primary" onClick={() => setIsModalVisible(true)}>
                添加数据
            </Button>
            <Table
                dataSource={dataList}
                columns={[
                    { title: '数据内容', dataIndex: 'dataContent', key: 'dataContent' },
                    { title: '操作', key: 'action', render: (_, record) => (
                        <div>
                            <Button onClick={() => handleEditData(record.id)}>修改</Button>
                            <Button danger onClick={() => handleDeleteData(record.id)}>删除</Button>
                        </div>
                    )}
                ]}
                rowKey="id"
            />
            <Modal
                title="数据操作"
                visible={isModalVisible}
                onCancel={() => setIsModalVisible(false)}
                onOk={currentData ? () => handleEditData(currentData.id) : handleAddData}
            >
                <Input.TextArea
                    rows={4}
                    placeholder="请输入数据内容"
                    value={newDataContent}
                    onChange={(e) => setNewDataContent(e.target.value)}
                />
            </Modal>
        </div>
    );
};

export default DataManagement;
